import pytest
@pytest.mark.activity
def test_add():
    n1 = 10
    n2 = 20
    sum = n1 + n2
    assert sum == 30
@pytest.mark.activity
def test_sub():
    n1 = 30
    n2 = 20
    sub = n1 - n2
    assert sub == 10
@pytest.mark.activity
def test_mul():
    n1 = 10
    n2 = 20
    prod = n1 * n2
    assert prod == 200
@pytest.mark.activity
def test_div():
    n1 = 10
    n2 = 2
    quot = n1 / n2
    assert quot == 5