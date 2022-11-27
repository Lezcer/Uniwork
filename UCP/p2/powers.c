#include <stdio.h>

int power2(void)
{
    static int base = 1;
    int result = base * base;
    base++;
    return result;
}
