def calcula_venda(valor, iof, ipi, lucro):
    imposto_iof = valor * iof
    imposto_ipi = valor * ipi
    lucro_desejado = valor * lucro
    venda = valor + imposto_iof + imposto_ipi + lucro_desejado
    return venda 

while True:
    entrada = input().split(",")
    if entrada[0] == "-": break
    valor_venda = calcula_venda(float(entrada[0]), float(entrada[1]), float(entrada[2]), float(entrada[3]))
    print(f"O valor de venda para este produto deve ser R$ {valor_venda:.2f}")


