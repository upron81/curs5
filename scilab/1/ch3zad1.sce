function y = f1(x)
    y = cotg(ntrhoot((1 + x^2), 3));
endfunction

x = linspace(-2, 2, 400);
y = f1(x);

plot(x, y);
xlabel("x");
ylabel("y");
title("График функции y");
xgrid();
