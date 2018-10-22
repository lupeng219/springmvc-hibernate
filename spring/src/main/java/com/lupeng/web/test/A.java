package com.lupeng.web.test;

/**
 * Created by lupeng on 2018/8/7.
 */
public class A {

    public A(){};

    public void a() {
        getEInStance().c();
        getFInStance().d();
    };

    public E getEInStance() {
        return new E();
    }

    public F getFInStance() {
        return new F();
    }

    class E extends C {
        public void ee(){};
        @Override
        public void c() {
            super.c();
        }
    }

    class F extends D {
        @Override
        public void d() {
            super.d();
        }
    }

}
