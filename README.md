## RevoGuild
Rewolucyjny system gildii na Twój serwer! ;)

=========

**Propozycje/błędy/bugi: [Issues](https://github.com/userMacieG/RevoGuild/issues)**

**Kontakt: [Discord](https://forum.cuddi.pl/showthread.php?tid=19)**

**Download: [Releases](https://github.com/userMacieG/RevoGuild/releases)**

=========

#### Konfiguracja:
(config.yml)
````yaml
config:
  enabled: false
  updater: true
  store:
    type: sqlite
    table-prefix: ks_
    mysql:
      host: localhost
      port: 3306
      username: root
      password: ''
      base-name: minecraft
    sqlite:
      base-name: minecraft.db
  name-tag:
    format: '&8[{COLOR}{TAG}&8] {COLOR}'
    color:
      no-guild: '&7'
      friend: '&a'
      friend-pvp: '&9'
      enemy: '&c'
      alliance: '&6'
  name:
    lenght-min: 2
    lenght-max: 22
    regex: '[a-zA-Z]+'
  tag:
    lenght-min: 2
    lenght-max: 5
    regex: '[a-zA-Z]+'
  chat:
    format:
      tag: '&8[&2{TAG}&8]&r '
      rank: '&8[&2{RANK}&8]&r '
    local:
      enabled: false
      char: '!'
      format:
        guild: '&2{PLAYER} -> Gildia: &a{MESSAGE}'
        alliance: '&6{TAG} {PLAYER} -> Sojusz: &e{MESSAGE}'
  ranking:
    start-points: 1000
    death:
      tag: '&7[&2{TAG}&7]&r '
      message: '&2Gracz {PGUILD}&7{PLAYER} ({LOSEPOINTS}) &2zostal zabity przez {KGUILD}&7{KILLER}
        ({WINPOINTS})&2!'
  effects:
    enabled: false
    chance: 50.0
    level:
      min: 0
      max: 3
    time:
      min: 10
      max: 3600
      interval: 5
    types:
    - FAST_DIGGING
    - REGENERATION
    - INCREASE_DAMAGE
  escape:
    enabled: true
    time: 10
    lose-points: 50
    broadcast: '&2Gracz {PGUILD}&7{PLAYER} &7(-50) &2wylogowal sie podczas walki!'
    disabled-commands:
      enabled: false
      commands:
      - g dom
      - dom
      - spawn
      notify:
        enabled: false
        message: '&4Blad: &cNie mozesz uzywac tej komendy podczas walki!'
  algorithm:
    ranking:
      win: (300 + (({KILLER_POINTS} - {PLAYER_POINTS}) * (-0.2)))
      lose: Math.abs({WIN_POINTS} / 2)
    guild-points: '{MEMBERS_POINTS} - ({MEMBERS_NUM} * 1000)'
    enlarge: ({CUBOID_SIZE} - 24) / 5 + 1
    join: Math.max(1, {MEMBERS_NUM} / 2)
  movement:
    notify:
      enabled: true
      intruder:
        enabled: true
  alliances:
    max: 3
  actions:
    block:
      break: false
      place: false
    bucket:
      empty: false
      fill: false
    protected-id:
    - 54
  uptake:
    enabled: false
    lives:
      start: 3
      max: 6
      time: 24
  treasure:
    enabled: false
    open-only-on-guild: true
    title: 'Skarbiec gildii:'
    rows: 6
  tnt:
    'off':
      enabled: false
      hours:
      - 0
      - 1
      - 2
      - 3
      - 4
      - 5
      - 6
      - 7
      - 8
    protection:
      enabled: false
      time: 24
    cant-build:
      enabled: false
      time: 90
    durability:
      enabled: false
      blocks:
      - OBSIDIAN 73.6
      - WATER 10.0
  items:
    create:
      normal: 1:0-10;
      vip: 1:0-5;
    join:
      normal: 1:0-10;
      vip: 1:0-5;
    leader:
      normal: 1:0-10;
      vip: 1:0-5;
    owner:
      normal: 1:0-10;
      vip: 1:0-5;
    enlarge:
      normal: 1:0-10;
      vip: 1:0-5;
    prolong:
      normal: 1:0-10;
      vip: 1:0-5;
    effect:
      normal: 1:0-10;
      vip: 1:0-10;
    alliance:
      normal: 1:0-10;
      vip: 1:0-10;
  cuboid:
    world: world
    size:
      start: 24
      max: 74
      add: 1
      between: 50
    spawn:
      enabled: true
      distance: 400
    disabled-commands:
      enabled: true
      commands:
      - g dom
      - g efekt
      - g skarbiec
      - g ustawdom
      notify:
        enabled: false
        message: '&4Blad: &cNie mozesz uzywac tej komendy na terenie innej gildii!'
  time:
    start: 3
    max: 14
    add: 7
    check: 3
    teleport: 10
  tablist:
    enabled: false
    refresh:
      interval: 10
    format:
      gtop: '{TAG} &7[&8{POINTS}&7]'
      ptop: '{NAME}'
      empty: brak
    header: '&6&lRevo&7&lGUILD'
    footer: '&7Online: &6{ONLINE}&7/&6300'
````
(tablist.yml)
````yaml
tablist:
  '0':
    '1': '&6Informacje:'
    '3': '&7Nick: &6{NAME}'
    '4': '&7Zabojstwa: &6{KILLS}'
    '5': '&7Smierci: &6{DEATHS}'
    '6': '&7Pozycja: &6{POSITION}'
    '7': '&7Punkty: &6{POINTS}'
    '8': '&7Gildia: &6{TAG}'
    '9': '&7Ping: &6{PING}&7ms'
    '18': '&7Godzina: &6{TIME}'
  '1':
    '1': '&6Ranking graczy:'
    '3': '&71.&6 {PTOP-1}'
    '4': '&72.&6 {PTOP-2}'
    '5': '&73.&6 {PTOP-3}'
    '6': '&74.&6 {PTOP-4}'
    '7': '&75.&6 {PTOP-5}'
    '8': '&76.&6 {PTOP-6}'
    '9': '&77.&6 {PTOP-7}'
    '10': '&78.&6 {PTOP-8}'
    '11': '&79.&6 {PTOP-9}'
    '12': '&710.&6 {PTOP-10}'
    '13': '&711.&6 {PTOP-11}'
    '14': '&712.&6 {PTOP-12}'
    '15': '&713.&6 {PTOP-13}'
    '16': '&714.&6 {PTOP-14}'
    '17': '&715.&6 {PTOP-15}'
    '18': '&716.&6 {PTOP-16}'
  '2':
    '1': '&6Ranking gildii:'
    '3': '&71.&6 {GTOP-1}'
    '4': '&72.&6 {GTOP-2}'
    '5': '&73.&6 {GTOP-3}'
    '6': '&74.&6 {GTOP-4}'
    '7': '&75.&6 {GTOP-5}'
    '8': '&76.&6 {GTOP-6}'
    '9': '&77.&6 {GTOP-7}'
    '10': '&78.&6 {GTOP-8}'
    '11': '&79.&6 {GTOP-9}'
    '12': '&710.&6 {GTOP-10}'
    '13': '&711.&6 {GTOP-11}'
    '14': '&712.&6 {GTOP-12}'
    '15': '&713.&6 {GTOP-13}'
    '16': '&714.&6 {GTOP-14}'
    '17': '&715.&6 {GTOP-15}'
    '18': '&716.&6 {GTOP-16}'
  '3':
    '1': '&7Github:'
    '2': '&6http://github.com/userMacieG/RevoGuild'
    '17': '&7Discord:'
    '18': '&6https://discordapp.com/invite/X5RDTJx'
update-slots:
  '0':
  - 3
  - 4
  - 5
  - 6
  - 7
  - 8
  - 9
  - 18
````
(commands.yml)
````yaml
commands:
  guild:
    user:
      main:
        name: gildia
        description: glowna komenda systemu gildii
        usage: /gildia <subkomenda>
        permission: revoguild.commands.guild
        aliases:
        - g
        - gildie
        - guild
      alliance:
        name: usun
        description: usuwa gildie
        usage: /g usun
        permission: revoguild.commands.guild.delete
        aliases:
        - delete
        - skasuj
      create:
        name: zaloz
        description: tworzy nowa gildie
        usage: /g zaloz <tag> <nazwa>
        permission: revoguild.commands.guild.create
        aliases:
        - create
        - utworz
        - nowa
      delete:
        name: usun
        description: usuwa gildie
        usage: /g usun
        permission: revoguild.commands.guild.delete
        aliases:
        - delete
        - skasuj
      effect:
        name: efekt
        description: losowanie efektu dla gildii
        usage: /g efekt
        permission: revoguild.commands.guild.effect
        aliases:
        - effect
        - buff
      enlarge:
        name: powieksz
        description: powiekszanie terenu gildii
        usage: /g powieksz
        permission: revoguild.commands.guild.enlarge
        aliases:
        - enlarge
        - resize
      home:
        name: dom
        description: teleportacja do domu gildii
        usage: /g dom
        permission: revoguild.commands.guild.home
        aliases:
        - home
        - baza
      info:
        name: info
        description: informacje o gildii
        usage: /g info <tag/nazwa>
        permission: revoguild.commands.guild.info
        aliases:
        - informacje
      invite:
        name: zapros
        description: zapraszanie do gildii
        usage: /g zapros <gracz>
        permission: revoguild.commands.guild.invite
        aliases:
        - invite
        - dodaj
      join:
        name: dolacz
        description: dolaczanie do gildii
        usage: /g usun
        permission: revoguild.commands.guild.delete
        aliases:
        - delete
        - skasuj
      kick:
        name: wyrzuc
        description: wyrzucanie czlonka gildii
        usage: /g wyrzuc <gracz>
        permission: revoguild.commands.guild.kick
        aliases:
        - kick
      leader:
        name: lider
        description: zmienianie lidera gildii
        usage: /g lider <gracz>
        permission: revoguild.commands.guild.leader
        aliases:
        - leader
      leave:
        name: opusc
        description: opuszczanie gildii
        usage: /g opusc
        permission: revoguild.commands.guild.leave
        aliases:
        - leave
      list:
        name: lista
        description: lista wszystkich gildii
        usage: /g lista
        permission: revoguild.commands.guild.list
        aliases:
        - list
        - top
      owner:
        name: zalozyciel
        description: zmienianie zalozyciela gildii
        usage: /g zalozyciel <gracz>
        permission: revoguild.commands.guild.owner
        aliases:
        - owner
      prolong:
        name: przedluz
        description: przedluzanie waznosci gildii
        usage: /g przedluz
        permission: revoguild.commands.guild.prolong
        aliases:
        - prolong
        - addtime
      pvp:
        name: pvp
        description: zmiana statusu pvp w gildii
        usage: /g pvp
        permission: revoguild.commands.guild.pvp
        aliases:
        - friendlyfire
        - ff
      sethome:
        name: ustawdom
        description: ustawianie domu gildii
        usage: /g ustawdom
        permission: revoguild.commands.guild.sethome
        aliases:
        - sethome
        - ustawbaze
      treasure:
        name: skarbiec
        description: zarzadzanie skarbcem gildii
        usage: /g skarbiec [dodaj <gracz>/usun <gracz>/lista]
        permission: revoguild.commands.guild.treasure
        aliases:
        - treasure
        - skrzynia
    admin:
      main:
        name: ga
        description: glowna komenda administratora systemu gildii
        usage: /ga <subkomenda>
        permission: revoguild.commands.admin.guild
        aliases:
        - gildieadmin
        - guildadmin
        - gildiaadmin
      ban:
        name: ban
        description: banowanie wybranej gildii
        usage: /ga ban <tag/nazwa> <czas> <powod>
        permission: revoguild.commands.admin.guild.ban
        aliases:
        - zbanuj
      delete:
        name: usun
        description: usuwanie wybranej gildii
        usage: /ga usun <tag/nazwa>
        permission: revoguild.commands.admin.guild.delete
        aliases:
        - delete
      kick:
        name: wyrzuc
        description: wyrzuca czlonka wybranej gildii
        usage: /ga wyrzuc <tag/nazwa> <gracz>
        permission: revoguild.commands.admin.guild.kick
        aliases:
        - kick
      reload:
        name: reload
        description: przeladowanie plikow konfiguracyjnych
        usage: /ga reload
        permission: revoguild.commands.admin.guild.reload
        aliases:
        - przeladuj
      set:
        name: set
        description: zmiana wartosci wybranej gildii
        usage: /ga set <tag/nazwa> <leader|owner|lives|pvp|size> <wartosc>
        permission: revoguild.commands.admin.guild.set
        aliases:
        - ustawrozmiar
      set-cuboid:
        name: setcuboid
        description: zmiana lokacji cuboida wybranej gildii
        usage: /ga setcuboid <tag/nazwa>
        permission: revoguild.commands.admin.guild.setcuboid
        aliases:
        - setcub
        - ustawteren
      teleport:
        name: teleport
        description: teleport do centrum wybranej gildii
        usage: /ga teleport <tag/nazwa>
        permission: revoguild.commands.admin.guild.teleport
        aliases:
        - tp
      unban:
        name: unban
        description: odbanowanie wybranej gildii
        usage: /ga unban <tag/nazwa>
        permission: revoguild.commands.admin.guild.unban
        aliases:
        - odbanuj
  ranking:
    user:
      name: ranking
      description: informacje o rankingu wybranego gracza
      usage: /ranking [gracz]
      permission: revoguild.commands.ranking
      aliases:
      - elo
      - gracz
      - points
    admin:
      main:
        name: ra
        description: glowna komenda administratora systemu rankingu
        usage: /ra <subkomenda>
        permission: revoguild.commands.admin.ranking
        aliases:
        - rankingadmin
      set:
        name: set
        description: ustawianie wartosci wybranego gracza
        usage: /ra set <gracz> <kills|deaths|points> <wartosc>
        permission: revoguild.commands.admin.ranking.set
        aliases:
        - ustaw
      reset:
        name: reset
        description: reset rankingu wybranego gracza
        usage: /ra reset <gracz>
        permission: revoguild.commands.admin.ranking.reset
        aliases:
        - resetuj
  combat:
    name: combat
    description: sprawdzanie czasu walki
    usage: /combat
    permission: revoguild.commands.combat
    aliases:
    - ct
    - tag
    - walka
    - pvp
    - logout
  top:
    name: top
    description: top 10 graczy
    usage: /top
    permission: revoguild.commands.top
    aliases:
    - topka
    - top10
````

Zapis ` 45: '&7|10.&r&6 |{GTOP-10}'` oznacza, że w 45 slocie zobaczymy właśnie taki tekst. Pustych slotów nie musimy definiować. Jeśli wartość w którymś slocie się powtórzy to dostaniemy crasha.

W sekcji `update-slots` wpisujemy ID slotów, które mają być aktualizowane w sposób ciągły (np. czas lub ping). Wszystkie wartości dotyczące rankingu gildii/graczy aktualizowane są wtedy, gdy się zmienią.

`PREFIX|NAME|SUFFIX` - prefix i suffix zmieniają się podczas odświeżania taba, name natomiast jest taki sam i reprezentuje dany team.
