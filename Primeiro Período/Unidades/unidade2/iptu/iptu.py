# PROG1 - UFCG 
# ALUNA: MARIA EDUARDA BATISTA DE FARIAS | DATA: 07/01/2021 | MATRÍCULA: 121110641
# EXERCÍCIO: PROGRAMA QUE CALCULA O IPTU

area = float(input("Área construída? "))
alíquota = float(input("Alíquota? "))

valor = area * alíquota + 35.00
porcentagem1 = (valor * 25) / 100
porcentagem2 = (valor * 5) / 100
quotaunica = valor - porcentagem1
seisparcelas = valor - porcentagem2
parcela1 = seisparcelas / 6 
parcelas10 = valor / 10

print(f"IPTU: R$ {valor:.2f}\n")
print("Pagamento:")
print(f"1. Quota única. R$ {quotaunica:.2f}")
print(f"2. Em 6 parcelas. Total: R$ {seisparcelas:.2f}")
print(f"   6 x R$ {parcela1:.2f}")
print(f"3. Em 10 parcelas. Total: R$ {valor:.2f}")
print(f"   10 x R$ {parcelas10:.2f}")
