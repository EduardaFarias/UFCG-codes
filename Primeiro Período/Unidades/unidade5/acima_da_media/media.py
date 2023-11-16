media_mensal = float(input())
lista = ""

while True:
    soma = 0
    cont = 0
    sequencia = input()
    if (sequencia == "fim"): break
    for num in sequencia.split():
        soma += int(num)
        cont += 1
    media = soma / cont
    if media <= (media_mensal / 2): break
    if media > media_mensal:
        lista += sequencia + ";"

for sequencia in lista.split(";"):
    if sequencia != "":
        print(sequencia)
        
