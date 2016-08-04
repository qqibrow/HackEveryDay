import argparse

parser = argparse.ArgumentParser(description="test argparse")
parser.add_argument('query', type=str, help='random query')
args = parser.parse_args()
print args
