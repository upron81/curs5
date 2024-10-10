function y = f2(x)
    y = 1 ./ tan(sqrt(1 + x.^2).^3);
endfunction

x = linspace(-2, 2, 400);
y = f2(x);

plot(x, y);
xlabel("x");
ylabel("y");
title("График функции y");
xgrid();
