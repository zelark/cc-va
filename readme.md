# CC Va
Веб-приложение для проверки номера кредитной/дебетовой карты. Проверка осуществялется с помощью [алгоритма Луна](https://en.wikipedia.org/wiki/Luhn_algorithm).

## Интсрументы необходимые для запуска
- [JDK 8 or later](https://adoptopenjdk.net/)
- [Clojure CLI](https://clojure.org/guides/getting_started#_clojure_installer_and_cli_tools)

## Запусков тестов
Для запуска тестов выполните
```
clj -M:test:runner
```

## Запуск приложения
Для запуска приложения выполните
```
clojure -M:app 3000
```

Дождитесь запуска сервера, после чего страница проверки будет доступна по адресу localhost:3000.
