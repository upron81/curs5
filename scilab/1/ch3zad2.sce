function y1 = f1(x)
    y1 = 1 - 3*x;
endfunction

function y2 = f2(x)
    y2 = x - sin(x);
endfunction

x = linspace(-5, 5, 400);
y1 = f1(x);
y2 = f2(x);

plot(x, y1, "-r", x, y2, "-b");
xlabel("x");
ylabel("y");
title("Графики функций y1 и y2");
legend("y1 = 1 - 3x", "y2 = x - sin(x)", "in_upper_right");
xgrid();

x_mark = [0, 1, -1];
y1_mark = f1(x_mark);
y2_mark = f2(x_mark);
plot(x_mark, y1_mark, "*r");
plot(x_mark, y2_mark, "*b");

xstring(1, 3, "Точка на графике y1");
xstring(-4, 1, "Точка на графике y2");
