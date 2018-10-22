package com.lupeng.web.socket.impl;

import com.lupeng.web.socket.MsgField;
import com.lupeng.web.socket.MsgPiece;

/**
 * 消息头
 * Created by lupeng on 2018/8/21.
 */
public class MessageHead extends MsgPiece {

    private Integer Total_Length;

    private Integer Command_Id;

    private Integer Sequence_Id;

    public Integer getTotal_Length() {
        return Total_Length;
    }

    public void setTotal_Length(Integer total_Length) {
        Total_Length = total_Length;
    }

    public Integer getCommand_Id() {
        return Command_Id;
    }

    public void setCommand_Id(Integer command_Id) {
        Command_Id = command_Id;
    }

    public Integer getSequence_Id() {
        return Sequence_Id;
    }

    public void setSequence_Id(Integer sequence_Id) {
        Sequence_Id = sequence_Id;
    }

    private static final MsgField[] items = new MsgField[]{
            new MsgField("Total_Length", 4, '0', MsgField.FillSide.RIGHT),
            new MsgField("Command_Id", 4, '0', MsgField.FillSide.RIGHT),
            new MsgField("Sequence_Id", 4, '0', MsgField.FillSide.RIGHT),

    };
    public MessageHead() {
        super(items);
    }
}
