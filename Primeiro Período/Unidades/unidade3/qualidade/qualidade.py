# PROG 1 - UFCG
# ALUNA:MARIA EDUARDA BATISTA DE FARIAS    | DATA:12/02/2022    | MATRÍCULA:121110641

peso_antes = float(input())
peso_depois = float(input()) 
porcentagem = 100 * (peso_antes - peso_depois) / peso_antes

print(f"{porcentagem:.1f}% do peso do produto é de água congelada.")
if porcentagem > 5 and porcentagem < 10:
    print("Produto em conformidade.")
elif porcentagem < 5:
    print("Produto qualis A.")
else:
    print("Produto não conforme.")
