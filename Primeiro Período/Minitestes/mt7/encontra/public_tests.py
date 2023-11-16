import unittest
import sys

module = sys.argv[-1].split(".py")[0]

class PublicTests(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        global elemento_faltando
        undertest = __import__(module)
        elemento_faltando = getattr(undertest, 'elemento_faltando', None)

    def test_basico1(self):
        lista1 = [1, 2, 3, 4]
        lista2 = [1, 2, 3, 4, 5]
        assert elemento_faltando(lista1, lista2) == 5
        assert lista1 == [1, 2, 3, 4]
        assert lista2 == [1, 2, 3, 4, 5]

    def test_basico2(self):
        lista1 = [1, 7, 6]
        lista2 = [6, 4, 7, 1]
        assert elemento_faltando(lista1, lista2) == 4
        assert lista1 == [1, 7, 6]
        assert lista2 == [6, 4, 7, 1]

if __name__ == '__main__':
    loader = unittest.TestLoader()
    runner = unittest.TextTestRunner()
    runner.run(loader.loadTestsFromModule(sys.modules[__name__]))
