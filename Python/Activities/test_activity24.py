import pytest
@pytest.mark.parametrize("earned, spent, expected", [ (30, 10, 20), (20, 2, 18) ])
# wallet_amt in below statement is a variable used as a function which is called in conftest file
def test_trans(wallet_amt, earned, spent, expected):
# wallet_amt is initialized as 0 in conftest file
    wallet_amt = wallet_amt+earned

    wallet_amt = wallet_amt-spent

    assert wallet_amt == expected 