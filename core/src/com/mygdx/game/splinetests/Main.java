package com.mygdx.game.splinetests;

import com.badlogic.gdx.math.BSpline;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Path;
import com.badlogic.gdx.math.Vector2;

import java.text.NumberFormat;

public class Main {
    public static void main(String[] args) {
        Vector2[] dataSet = new Vector2[] {new Vector2(0.0f,4.0f), new Vector2(1.0f,1.0f), new Vector2(4.0f,0.0f)};
//        Path<Vector2> path = new CatmullRomSpline<>(dataSet, false);
        Path<Vector2> path = new Bezier<>(dataSet);
//        Path<Vector2> path = new BSpline<>(dataSet, 1, true);
        Vector2 out = new Vector2();
        for (float t = 0.0f; t < 1.0f; t += 0.03) {
            path.valueAt(out, t);
            String x = String.format("%.2f ", out.x);
            String y = String.format("%.2f", out.y);
            System.out.printf(x.replace(",", "."));
            System.out.printf(y.replace(",", ".") );
            System.out.println();
        }
    }
}
