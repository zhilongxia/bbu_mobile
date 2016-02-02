package bbu_mobile.com.netty.tcp;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.buybuyup.common.Global;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

public class MyWebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

	private static final Logger logger = Logger.getLogger(WebSocketServerHandshaker.class.getName());

	private WebSocketServerHandshaker handshaker;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		// ���
		Global.group.add(ctx.channel());

		System.out.println("�ͻ������������ӿ���");

	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		// �Ƴ�
		Global.group.remove(ctx.channel());

		System.out.println("�ͻ������������ӹر�");

	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {

		if (msg instanceof FullHttpRequest) {

			handleHttpRequest(ctx, ((FullHttpRequest) msg));

		} else if (msg instanceof WebSocketFrame) {

			handlerWebSocketFrame(ctx, (WebSocketFrame) msg);

		}

	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {

		// �ж��Ƿ�ر���·��ָ��
		if (frame instanceof CloseWebSocketFrame) {
			handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
		}

		// �ж��Ƿ�ping��Ϣ
		if (frame instanceof PingWebSocketFrame) {
			ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
			return;
		}

		// �����̽�֧���ı���Ϣ����֧�ֶ�������Ϣ
		if (!(frame instanceof TextWebSocketFrame)) {

			System.out.println("�����̽�֧���ı���Ϣ����֧�ֶ�������Ϣ");

			throw new UnsupportedOperationException(
					String.format("%s frame types not supported", frame.getClass().getName()));
		}

		// ����Ӧ����Ϣ
		String request = ((TextWebSocketFrame) frame).text();

		System.out.println("������յ���" + request);

		if (logger.isLoggable(Level.FINE)) {
			logger.fine(String.format("%s received %s", ctx.channel(), request));
		}

		TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString() + ctx.channel().id() + "��" + request);

		// Ⱥ��
		Global.group.writeAndFlush(tws);

		// ���ء�˭���ķ���˭��
		// ctx.channel().writeAndFlush(tws);

	}

	private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {

		if (!req.getDecoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {

			sendHttpResponse(ctx, req,
					new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));

			return;
		}

		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
				"ws://localhost:7397/websocket", null, false);

		handshaker = wsFactory.newHandshaker(req);

		if (handshaker == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
		} else {
			handshaker.handshake(ctx.channel(), req);
		}

	}

	private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {

		// ����Ӧ����ͻ���
		if (res.getStatus().code() != 200) {
			ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
			res.content().writeBytes(buf);
			buf.release();
		}

		// ����Ƿ�Keep-Alive���ر�����
		ChannelFuture f = ctx.channel().writeAndFlush(res);
		if (!isKeepAlive(req) || res.getStatus().code() != 200) {
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}

	private static boolean isKeepAlive(FullHttpRequest req) {

		return false;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		cause.printStackTrace();
		ctx.close();

	}

}
