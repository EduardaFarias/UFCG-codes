q_jogadores = 0
while True:
    nome = input()
    if nome == "-": break
    q_jogadores += 1

if q_jogadores == 5:
    print("Modalidade -> Basquete")
elif q_jogadores == 6:
    print("Modalidade -> Vôlei")
elif q_jogadores == 11:
    print("Modalidade -> Futebol")
elif q_jogadores  == 7:
    print("Modalidade -> Handebol")

