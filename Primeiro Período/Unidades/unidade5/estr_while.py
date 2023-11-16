# WHILE CONDIÇÃO
#Exemplo de como seria a estrutura no for:

for i in range(5):
    print(i, i ** 2)

#Fazendo com while:
i = 0                 #inicialização
while i < 5:          #condição; Menor que 5 porque o range é 5, mas não usa range no while
    print(i, i ** 2)  #corpo
    i = i + 1         #atualização



#WHILE TRUE
print("x")

while True:        #REPEAT  
    entrada = input()
    n = int(entrada) #Aqui eu tô pegando a entrada transformando em um inteiro, e aguardando na variável n
    if n > 0: break  #UNTIL(condição de saída)

print("Y")
print(n, n ** 0.5)


#LOOP LEAVE


