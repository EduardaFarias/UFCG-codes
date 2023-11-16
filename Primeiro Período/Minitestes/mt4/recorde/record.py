# # PROG1 - UFCG
# ALUNA: EDUARDA FARIAS |  DATA:21/03/2022  | MATRÍCULA: 121110641
# EXERCÍCIO: Programa que verifica se o recorde no novo candidato
#            ultrapassou ou não o recorde atual, ou se é igual ao
#            recorde atual   
recorde_atual = float(input())
recorde_novo_cadidato = float(input()) 
if recorde_atual and recorde_novo_cadidato >= 0:
    if recorde_novo_cadidato < recorde_atual:
        print("recorde mantido")
    elif recorde_novo_cadidato > recorde_atual:
        print(f"recorde quebrado ({recorde_novo_cadidato:.2f} kg)")
    else:
        recorde_atual == recorde_novo_cadidato
        print("recorde empatado")
