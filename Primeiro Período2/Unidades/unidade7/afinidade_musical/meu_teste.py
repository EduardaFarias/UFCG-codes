import unittest
import sys

undertest = __import__(sys.argv[-1].split(".py")[0])
tem_afinidade = getattr(undertest, 'tem_afinidade', None)

class PublicTests(unittest.TestCase):

    def test_exemplo(self):
        l1 = ['zeze', 'bruno e marrone', 'joao', 'pedro', 'u2']
        l2 = ['zeze', 'joao', 'u2', 'jquest']
       assert tem_afinidade(l1, l2) == True
    

    def um_igual(self):
        l1 = ['beyonce', 'anitta', 'bts', 'red hot', 'u2']
        l2 = ['jorginho', 'carminha', 'zayn', 'bts']
        assert tem_afinidade(l1, l2) == False

    def dois_iguais(self):
        l1 = ['beyonce', 'anitta', 'bts', 'red hot', 'u2']
        l2 = ['beyonce', 'anitta', 'zayn', 'jão']
        assert tem_afinidade(l1, l2) == False

if __name__ == '__main__':
    loader = unittest.TestLoader()
    runner = unittest.TextTestRunner()
    runner.run(loader.loadTestsFromModule(sys.modules[__name__]))
