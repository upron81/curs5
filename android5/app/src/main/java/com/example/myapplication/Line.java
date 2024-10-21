package com.example.myapplication;

public class Line {
    public float x1, y1, x2, y2;
    public float A, B, C;
    private static final float EPSILON = 0.0001f;  // Погрешность для сравнения

    public Line(float x1, float y1, float x2, float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        // Вычисляем коэффициенты A, B, C уравнения прямой
        A = y1 - y2;
        B = x2 - x1;
        C = x1 * y2 - x2 * y1;
    }

    // Метод для проверки пересечения линий
    public boolean isIntersect(Line other) {
        float det = this.A * other.B - other.A * this.B;
        return Math.abs(det) > EPSILON; // Если определитель больше погрешности, линии пересекаются
    }

    // Метод для проверки совпадения линий с учетом погрешности
    public boolean isCoincident(Line other) {
        return Math.abs(this.A * other.B - other.A * this.B) < EPSILON &&
                Math.abs(this.A * other.C - other.A * this.C) < EPSILON;
    }
}
