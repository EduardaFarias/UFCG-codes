palavra = input()
nova_palavra = ""
trocas = 0
nova_palavra += palavra[0]
for i in range(1,len(palavra)):
    if palavra[i] == "a" or palavra[i] == "A":
        nova_palavra += "4"
        trocas += 1
    elif palavra[i] == "e" or palavra[i] == "E":
        trocas += 1
        nova_palavra += "3"
    elif palavra[i] == "i" or palavra[i] == "I":
        nova_palavra += "1"
        trocas += 1
    elif palavra[i] == "o" or palavra[i] == "O":
        nova_palavra += "0"
        trocas += 1
    else:
        nova_palavra += palavra[i]
print(f"{nova_palavra} ({trocas} troca(s))")