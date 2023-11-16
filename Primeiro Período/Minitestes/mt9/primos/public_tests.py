import unittest
import sys

module = sys.argv[-1].split(".py")[0]

class PublicTests(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        global seleciona_primos
        undertest = __import__(module)
        seleciona_primos = getattr(undertest, 'seleciona_primos', None)

    def test_1_publico(self):
        lista = [3, 6, 9, 12, 15, 18, 19, 21]
        assert seleciona_primos(lista) == [3, 19]
        assert lista == [3, 6, 9, 12, 15, 18, 19, 21]


if __name__ == '__main__':
    loader = unittest.TestLoader()
    runner = unittest.TextTestRunner()
    runner.run(loader.loadTestsFromModule(sys.modules[__name__]))
