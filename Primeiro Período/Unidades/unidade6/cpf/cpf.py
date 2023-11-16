def calcula_digitos_verificacao(cpf):
  multiplica = 2
  lista1 = []
  for digito in range(len(cpf) -1 , -1, -1):
      primeiro_digito = int(cpf[digito]) * multiplica
      multiplica += 1
      lista1.append(primeiro_digito)

  soma1 = 0
  for numero in lista1:
      soma1 += numero

  soma1 = (soma1 * 10) % 11
  str_soma1 = str(soma1)

  nova_soma = cpf + str_soma1

  lista2 = []
  multiplica = 2
  for i in range(len(nova_soma) -1, -1, -1):
      segundo_digito = int(nova_soma[i]) * multiplica
      multiplica += 1
      lista2.append(segundo_digito)

  soma2 = 0
  for numero in lista2:
      soma2 += numero

  soma2 = (soma2 * 10) % 11
  str_soma2 = str(soma2)

  return str_soma1 + str_soma2

assert calcula_digitos_verificacao('153245875') == '40'













