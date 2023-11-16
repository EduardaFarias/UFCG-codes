reprovados = 0 

while True:
    presenca_aluno = input()
    if presenca_aluno == "-": break   

    iterador = 0
    faltas = 0
    while iterador < len(presenca_aluno):
        if presenca_aluno[iterador] == 'f':
            faltas += 1
        iterador +=1
   
    if faltas > 8:
        reprovados += 1
print(f"{reprovados} aluno(s) reprovado(s) por falta.")


