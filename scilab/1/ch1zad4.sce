xn = -5;
xk = 5;
dx = 0.1;

x = xn:dx:xk;
y = zeros(size(x));

for i = 1:length(x)
    if x(i) >= 3
        y(i) = sin(x(i)) / x(i);
    elseif x(i) > -3 && x(i) < 3
        y(i) = cos(x(i)) / 2;
    else
        y(i) = x(i)^3;
    end
end

disp(y);
