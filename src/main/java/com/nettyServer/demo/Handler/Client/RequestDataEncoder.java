package com.nettyServer.demo.Handler.Client;

import com.nettyServer.demo.Entity.RequestData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

public class RequestDataEncoder extends MessageToByteEncoder<RequestData> {
    private final Charset charset=Charset.forName("UTF-8");

    @Override
    protected void encode(ChannelHandlerContext ctx, RequestData msg, ByteBuf output) throws Exception{
        output.writeInt(msg.getIntValue());
        output.writeInt(msg.getStringValue().length());
        output.writeCharSequence(msg.getStringValue(),charset);
    }
}
