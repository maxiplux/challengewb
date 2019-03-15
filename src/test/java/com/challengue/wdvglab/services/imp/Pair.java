package com.challengue.wdvglab.services.imp;

import lombok.Data;

@Data(staticConstructor = "of")
public class Pair<A, B> {
    private final A left;
    private final B right;


    public Pair(A test, B test1) {
        this.left=test;
        this.right=test1;
    }
}
