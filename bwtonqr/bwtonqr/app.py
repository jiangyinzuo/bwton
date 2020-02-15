# -*- coding: utf-8 -*-


"""
二维码生成器
"""

import bwtonqr

img = bwtonqr.make("www.baidu.com")

with open('test.png', 'wb') as f:
    img.save(f)
