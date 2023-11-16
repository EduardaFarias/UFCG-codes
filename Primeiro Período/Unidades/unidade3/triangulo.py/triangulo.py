def eh_triangulo(a, b, c):
    if abs(b -c) <  a  < b + c and abs(a - c) < b < a + c and abs(a - b) < c < a + b:
        return True
    return False

a = int(input())
b = int(input())        
c = int(input())
perimetro = a + b + c        
resultado = f"triangulo valido. {perimetro}" if eh_triangulo(a, b, c) == True else "triangulo invalido"

print(resultado)

