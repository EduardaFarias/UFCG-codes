# Uma função em uma linguagem de programação é uma sequência de comandos que executa alguma
#tarefa e que tem um nome,permite obter valores a partir de outros 
#Evita a repetição e manutenção de códigos 
# Dividida em dois momentos:
# Definição da função
# Invocação da função

def <nome_da_função>(parametro1, parâmetro2,...,parametro_n):
    <corpo da função: linha1>
    <corpo da função: linha2>
    ...
    <return><valor ou expressão> #se não explictar o return, ele retorna None

#Exemplo, sem função:
a1, b1, c1  = 6, 8, 2
a2, b2, c2 = 9, 24, 16
a3, b3, c3 = 1, -2, 4 

delta1 = b1 ** 2 - 4 * a1 * c1
delta2 = b2 ** 2 - 4 * a2 * c2
delta3 = b3 ** 2 - 4 * a3 * c3

print(delta1)
print(delta2)
print(delta3)

#DEFININDO A FUNÇÃO:
def delta(a,b,c):
    d = b ** 2 - 4 * a * c
    return d 

a1, b1, c1 = 6, 8, 2
print((delta(a1, b1, c1)) #Chamando a função no código 


#Outro exemplo de função, que não recebe parâmetro

def print_olá():
    nome = input()
    saida = "olá," + nome + "!"
    print(saida)


 # FUNÇÃO COM VALORES DEFAULT    
def imprime_pa(a0, n, razao = 1):


    for i in range(n):
        termo = a0 + i * razao
        print(termo)


                                                #ASSERTS

#Como testar um programa que tem funções? Usando ASSERTS
#asserts são usados para descrever especificações e testes de funções:
#   - FUNÇÃO QUE CONTA VOGAIS DE UMA PALAVRA:
        # assert conta_vogais("banana") == 3
        # assert conta_vogais("Antonino") == 4
        #assert conta_vogais("tv") == 0
#   - NO CASO DO NOSSO EXEMPLO DO CÁLCULO DE DELTA:
        # assert delta(6, 8, 2) == 16
        # assert delta(9, 24, 16) == 0
        # assert delta(1, -2, 4) == -12
#Se o assert for TRUE ele fica calado, se o assert der False, ele dá sinal de que está errado 

def duplica(lista):
    for i in range(len(lista)):
        lista[i] = 2 * lista[i] #Convertendo lista, para cada elemento da minha lista, agora vai estar duplicado


l = [1, 2, 3, 4, 5]
duplica(l)
print(l)

assert duplica(l) == None
assert l == [2, 4, 6, 8, 10]
