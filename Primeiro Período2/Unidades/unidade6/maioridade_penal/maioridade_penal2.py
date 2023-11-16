def maioridade_penal(str_nomes, str_idades):
    nomes = str_nomes.split()
    idades = str_idades.split()
    
    maiores_de_idade = ""
    for i in range(len(nomes)):
        if int(idades[i]) >= 18:
            maiores_de_idade += nomes[i]
            if i != len(nomes) - 1:
                maiores_de_idade += " "
    return maiores_de_idade

assert maioridade_penal("Jansen Italo Ana","14 21 60") == "Italo Ana"

#resultado = maioridade_penal("Jansen Italo Ana", "14 21 60")
#print(resultado)                             

