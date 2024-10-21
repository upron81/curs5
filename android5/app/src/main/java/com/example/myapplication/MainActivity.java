package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Упрощенный пример массива линий для отладки
        List<Line> lines = new ArrayList<>();

        // Совпадающие линии
        lines.add(new Line(100, 100, 200, 200));  // Прямая 1
        lines.add(new Line(100, 100, 200, 200));  // Прямая 2 (точная копия Прямой 1)

        // Пересекающиеся линии
        lines.add(new Line(100, 100, 300, 300));  // Прямая 3
        lines.add(new Line(100, 300, 300, 100));  // Прямая 4

        // Параллельные линии
        lines.add(new Line(100, 400, 300, 400));  // Прямая 5
        lines.add(new Line(100, 450, 300, 450));  // Прямая 6

        // Устанавливаем кастомное представление с линиями
        setContentView(new LineView(this, lines));
    }
}
