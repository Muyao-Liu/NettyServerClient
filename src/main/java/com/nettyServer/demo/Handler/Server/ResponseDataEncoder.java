package com.nettyServer.demo.Handler.Server;

import com.nettyServer.demo.Entity.ResponseData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ResponseDataEncoder extends MessageToByteEncoder<ResponseData> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ResponseData msg, ByteBuf output) throws Exception{
        output.writeInt(msg.getIntValue());
    }
}
