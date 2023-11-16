def tem_afinidade(l1, l2): 
    q_iguais = 0
    for artista in l1:
        for cantor in l2:
            if artista == cantor:
                q_iguais += 1

    if q_iguais >= 3:
        return True
    return False

l1 = ['zeze', 'bruno e marrone', 'joao', 'pedro', 'u2']
l2 = ['zeze', 'joao', 'u2', 'jquest']
assert tem_afinidade(l1, l2) == True




