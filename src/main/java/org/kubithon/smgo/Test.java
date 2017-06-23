package org.kubithon.smgo;

import org.kubithon.smgo.client.ShowInfos;

public class Test {
    public static void main(String[] args) {
        ShowInfos showInfos = ShowInfos.read(ShowInfos.class.getResourceAsStream("show_test.json"));

        System.out.println(showInfos.getTimeline()
                                    .get(0)
                                    .get(0)
                                    .getParameters()
                                    .getY()
                                    .getExpression());
    }
}
