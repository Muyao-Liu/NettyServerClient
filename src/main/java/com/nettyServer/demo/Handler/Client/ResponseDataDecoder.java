package com.nettyServer.demo.Handler.Client;

import com.nettyServer.demo.Entity.ResponseData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class ResponseDataDecoder extends ReplayingDecoder<ResponseData> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf input, List<Object> output) throws Exception{
        ResponseData responseData=new ResponseData();
        responseData.setIntValue(input.readInt());
        output.add(responseData);
    }
}
