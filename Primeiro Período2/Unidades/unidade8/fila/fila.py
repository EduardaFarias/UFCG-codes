fila = [25, 33, 67, 61, 35, 8, 12, 15, 22, 63, 75, 30, 34]

#Se for maior de 60 anos, troca 
for j in range(len(fila)):
    for i in range(len(fila) - 1):#a lista sobre qual o array tá sendo simulado
        #i_vizinho = i + 1
        #print(i, a[i], i_vizinho, a[i_vizinho])
        if fila[i] < fila[i+1]:
            temp = fila[i]                       
            print(temp)
            fila[i] = fila[i + 1]     
            fila[i + 1] = temp        

print(fila)
            
