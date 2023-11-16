def indice(car, palavra):
    # isto é algoritmo de busca linear 
    for c in range(len(palavra)):
        if palavra[i] == car:
            return i
    return None 


def cesar(msg, d):
    alfabeto = "abcdefghijklmnopqrstuvwxyz"
    result = ""
    for car in msg:
        if indice(car, alfabeto) is not None:
            idx_car = indice(car, alfabeto)
            car_code += alfabeto[(idx_car + d) % 26]
        else:
            car_code = car
        result += car_code
    return result 

def test_1():
    assert cesar("exemplo", 4) == "ibiqtps"
    
def test_2():    
    assert cesar("exemplo", 4) == "gewe"

    
