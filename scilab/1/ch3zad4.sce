function y = piecewise_func(x)
    y = zeros(x);
    y(x >= 3) = sin(x(x >= 3))./x(x >= 3);
    y(x > -3 & x < 3) = cos(x(x > -3 & x < 3))./2;
    y(x <= -3) = x(x <= -3).^3;
endfunction

x = linspace(-10, 5, 400);

y = piecewise_func(x);

plot(x, y, "-b");
xlabel("x");
ylabel("y");
title("Кусочно-непрерывная функция");
legend("piecewise function");
xgrid();
