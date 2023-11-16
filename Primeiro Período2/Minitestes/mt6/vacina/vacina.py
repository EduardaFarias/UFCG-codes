total = 0
nao = 0

while True:
    fila = input()
    if fila == '###':
        break
    total += 1

    pessoa = 0 
    while pessoa + 1 < len(fila):
        if fila[pessoa] == 'a' and (fila[pessoa + 1] == 'i' or fila[pessoa +1] == 'c'):
            nao += 1
            break
        pessoa += 1

sim = total - nao

print(f"sim: {sim}")
print(f"não: {nao}")
