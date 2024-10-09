function y = piecewise_func(x)
    y = zeros(x);
    y(x > 0) = 1 - 3*x(x > 0);
    y(x >= -5 & x <= 0) = x(x >= -5 & x <= 0) - sin(x(x >= -5 & x <= 0));
    y(x < -5) = x(x < -5).^2;
endfunction

x = linspace(-10, 5, 400);

y = piecewise_func(x);

plot(x, y, "-b");
xlabel("x");
ylabel("y");
title("Кусочно-непрерывная функция");
legend("piecewise function");
xgrid();
