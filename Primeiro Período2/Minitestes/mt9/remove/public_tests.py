# coding: utf-8
import unittest
import sys

module = sys.argv[-1].split(".py")[0]

class PublicTests(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        global remove_maiores
        undertest = __import__(module)
        remove_maiores = getattr(undertest, 'remove_maiores', None)


    def test_1(self):
        lista1 = [8, 7, 3, 8, 2]
        assert remove_maiores(lista1) == None
        assert lista1 == [7, 3, 2]


if __name__ == '__main__':
    loader = unittest.TestLoader()
    runner = unittest.TextTestRunner()
    runner.run(loader.loadTestsFromModule(sys.modules[__name__]))
