package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.util.List;

public class LineView extends View {
    public List<Line> lines;
    public Paint linePaint;
    public Paint coincidentLinePaint;

    public LineView(Context context, List<Line> lines) {
        super(context);
        this.lines = lines;

        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(5);

        coincidentLinePaint = new Paint();
        coincidentLinePaint.setColor(Color.RED);  // Совпадающие линии будем рисовать красным
        coincidentLinePaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Сначала рисуем совпадающие линии (красные)
        for (int i = 0; i < lines.size(); i++) {
            Line line1 = lines.get(i);
            for (int j = i + 1; j < lines.size(); j++) {
                Line line2 = lines.get(j);
                if (line1.isCoincident(line2)) {
                    Log.d("LineView", "Линии " + i + " и " + j + " совпадают");
                    // Рисуем совпадающие линии красным цветом
                    canvas.drawLine(line1.x1, line1.y1, line1.x2, line1.y2, coincidentLinePaint);
                    canvas.drawLine(line2.x1, line2.y1, line2.x2, line2.y2, coincidentLinePaint);
                }
            }
        }

        // Затем рисуем пересекающиеся линии (чёрные)
        for (int i = 0; i < lines.size(); i++) {
            Line line1 = lines.get(i);
            for (int j = i + 1; j < lines.size(); j++) {
                Line line2 = lines.get(j);
                if (line1.isIntersect(line2) && !line1.isCoincident(line2)) {
                    Log.d("LineView", "Линии " + i + " и " + j + " пересекаются");
                    // Рисуем только пересекающиеся линии
                    canvas.drawLine(line1.x1, line1.y1, line1.x2, line1.y2, linePaint);
                    canvas.drawLine(line2.x1, line2.y1, line2.x2, line2.y2, linePaint);
                }
            }
        }
    }
}
