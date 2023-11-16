#ENTRADA: Média mensal estabelecida pela secretária de segurança pública   100.0
                                                                        # 77 72 65                                        
#2ºParte da entrada: Quantidades de ocorrencias resgistradas por dia      101 110                      
#FAZER A MÉDIA DESSAS ENTRADAS SOMA/ 29

#LER AS SEQUÊNCIAS DA SEGUNDA LINHA, VER QUEM É MAIOR QUE A MÉDIA

#SAÍDA = SEQUENCIAS MAIORES QUE A MÉDIA, PRA CADA SEQUENCIA RESERVE UMA LINHA

media_mensal = input("Digite aqui a média mensal:")
lista = []
while True:
    sequencia = input().split()
    if sequencia == ["fim"]:
        break
    lista = sequencia

for num in range(len(lista)):
    lista[num] = int(lista[num])

soma = 0

for num in range(len(lista)):
    






    

