# PROG1 - UFCG
# ALUNA: EDUARDA FARIAS |  DATA:21/03/2022  | MATRÍCULA: 121110641
# EXERCÍCIO: Programa que calcula a comissão de um vendedor, caso 
# o valor da venda seja menor ou igual a R$ 500.00 é calculado a 
# comissão e é exibido na saída o valor da comissão e para qual 
# vendedor ela é destinada. Caso o valor da venda seja maior que
# R$ 500.00 é calculada a comisão, e é exibido na saída o valor da 
# comissão e para qual vendedor ela é destinada, contudo apenas as
# 4 primeiras letras do nome do vendedor.

vendedor = input("Qual é o nome do(a) vendedor(a)? ")
valor_da_venda = float(input("Qual é o valor da venda? "))

if valor_da_venda <= 500.00:
    print(f"O valor da comissão para o(a) vendedor(a) {vendedor} é R$ {valor_da_venda * 0.05:.2f}.")
else:
    print(f"O valor da comissão para o(a) vendedor(a) {vendedor [0:5]} é R$ {valor_da_venda * 0.10:.2f}.")    

#outra forma de fazer sem o slice
# palavra = "eduarda"
# palavraNova = ""
# for letra in range(len(palavra) -1):
#     palavraNova += palavra[Letra]
# print(PalavraNova)
