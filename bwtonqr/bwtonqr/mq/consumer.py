# -*- coding: utf-8 -*-

"""
消息队列客户端
"""

import pika
from pika import BlockingConnection
from pika.adapters.blocking_connection import BlockingChannel

connection: BlockingConnection = pika.BlockingConnection(
    pika.ConnectionParameters(host='192.168.56.1'))
channel: BlockingChannel = connection.channel()

channel.queue_declare(queue='test001', durable=True)
channel.basic_consume(
    queue='test001',
    auto_ack=True,
    on_message_callback=lambda ch, method, properties, body: print(body))
channel.start_consuming()
