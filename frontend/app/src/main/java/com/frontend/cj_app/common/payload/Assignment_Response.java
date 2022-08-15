package com.frontend.cj_app.common.payload;

import com.frontend.cj_app.common.model.AssignmentList;

public class Assignment_Response {
    private AssignmentList first;
    private AssignmentList mid;
    private AssignmentList end;
    private String REPL_CD;
    private String REPL_MSG;

    public AssignmentList getFirst() {
        return first;
    }

    public void setFirst(AssignmentList first) {
        this.first = first;
    }

    public AssignmentList getMid() {
        return mid;
    }

    public void setMid(AssignmentList mid) {
        this.mid = mid;
    }

    public AssignmentList getEnd() {
        return end;
    }

    public void setEnd(AssignmentList end) {
        this.end = end;
    }

    public String getREPL_CD() {
        return REPL_CD;
    }

    public void setREPL_CD(String REPL_CD) {
        this.REPL_CD = REPL_CD;
    }

    public String getREPL_MSG() {
        return REPL_MSG;
    }

    public void setREPL_MSG(String REPL_MSG) {
        this.REPL_MSG = REPL_MSG;
    }
}
