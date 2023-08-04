package com.nettyServer.demo.Handler.Server;

import com.nettyServer.demo.Entity.RequestData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.util.List;

//decode from input byte buf
public class RequestDecoder extends ReplayingDecoder<RequestData> {
    private final Charset charset=Charset.forName("UTF-8");

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf input, List<Object> output) throws Exception{
        RequestData requestData=new RequestData();
        requestData.setIntValue(input.readInt());

        int strLen=input.readInt();
        requestData.setStringValue(input.readCharSequence(strLen,charset).toString());
        output.add(requestData);
    }
}
