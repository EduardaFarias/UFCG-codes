diametro = float(input("Medida do diâmetro? "))
altura = float(input("Medida da altura? ")) 

raio = diametro / 2
areabase = 3.14 * raio ** 2
arealateral = (2 * 3.14) * raio * altura

print(f"Área calculada: {(2 * areabase) + arealateral:.2f}")


