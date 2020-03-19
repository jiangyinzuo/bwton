# -*- coding: utf-8 -*-

import os

from celery import Celery

from dotenv import load_dotenv, find_dotenv

load_dotenv(find_dotenv())

broker_url = os.environ.get('broker_url')
assert broker_url is not None
app = Celery('mock_request', broker=broker_url, backend='rpc://')
app.config_from_object('celeryconfig')


@app.task(reply_to='result', queue='queue_123',  results_queue='my_result_queue')
def hello():
    print('hhh')
    return 'hello world'


if __name__ == '__main__':
    pass
