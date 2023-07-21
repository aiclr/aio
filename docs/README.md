![jekyll-gh-pages](https://github.com/bougainvilleas/aio/actions/workflows/jekyll-gh-pages.yml/badge.svg?branch=snapshot&event=pull_request_target)
![jekyll-gh-pages](https://github.com/bougainvilleas/aio/actions/workflows/jekyll-gh-pages.yml/badge.svg?branch=snapshot)

# jekyll

## install

> [Installation](https://jekyllrb.com/docs/installation/)\
> [Jekyll on Linux](https://jekyllrb.com/docs/installation/other-linux/)\
> [Jekyll on Ubuntu](https://jekyllrb.com/docs/installation/ubuntu/)\
> `sudo pacman -S ruby base-devel`
> ```shell
> echo '# Install Ruby Gems to ~/gems' >> ~/.zshrc
> echo 'export GEM_HOME="$HOME/gems"' >> ~/.zshrc
> echo 'export PATH="$HOME/gems/bin:$PATH"' >> ~/.zshrc
> source ~/.zshrc
> ```
> `gem install jekyll bundler`\
> `echo 'export PATH="$HOME/.local/share/gem/ruby/3.0.0/bin:$PATH"' >> ~/.zshrc`
> `source ~/.zshrc`

## tutorial

> [Step by Step Tutorial](https://jekyllrb.com/docs/step-by-step/01-setup/)\
> `jekyll build`\
> `jekyll serve`\
> [http://localhost:4000](http://localhost:4000)

## highlighter

[Rouge's documentation](https://rouge-ruby.github.io/docs/Rouge/CSSTheme.html)

> 语法高亮\
> `rougify style github > _sass/syntax.scss`\
> `rougify style magritte > _sass/syntax.scss`\
> `rougify style pastie > _sass/syntax.scss`\
> `rougify style base16 > _sass/syntax.scss`\
> `rougify style gruvbox > _sass/syntax.scss`\
> `rougify style igorpro > _sass/syntax.scss`\
> `rougify style molokai > _sass/syntax.scss`\
> `rougify style monokai > _sass/syntax.scss`\
> `rougify style monokai.sublime > _sass/syntax.scss`\
> `rougify style tulip > _sass/syntax.scss`\
> `echo "@import "syntax";" >> assets/css/styles.scss`
