<p align="center">
  <a href="http://www.codetally.com/">
    <img src="http://www.codetally.com/codetally_help_logo_ns.png" width=72>
  </a><h3 align="center">Codetally</h3><p align="center">
    Tally code costs automatically.
    <br>
    <a href="http://www.codetally.com/"><strong>Visit Codetally &raquo;</strong></a>
  </p>
</p>
<br>

## Table of Contents

- [Quick start](#quick-start)
- [Charges](#charges)
- [Time Logs](#time-logs)
- [Installation](#installation)
- [Shield](#shield)
- [Logging](#logging)
- [History](#history)
- [Bugs and feature requests](#bugs-and-feature-requests)
- [Community](#community)
- [Creators](#creators)
- [Copyright and license](#copyright-and-license)

## Quick start

Several quick start options are available:

- Download an example [Charge](http://www.codetally.com/codetally.json) and [Timelog](http://www.codetally.com/timelog.json) file and add them to your Github repo.
- Clone the repo: `git clone https://github.com/Codetally/codetally_example.git`

## Charges

A charge is a cost for a defined unit of work. Here is an example of a `codetally.json` file containing a charge:

```json
{
  "currency":"cad",
  "charges": [
    {
      "event":"commit",
      "action":"added",
      "chargetype":"author",
      "chargeref":"someone@github.org",
      "description":"Author specific charge for Greg when he adds something in a commit.",
      "calculationtype":"normal",
      "chargeamount":"45.23"
    }
  ]
}
```
An individual charge contains the following properties:
- **Event**: An event represents a change to the repo. Currently, Codetally only supports commit events.
- **Action**: Within the context of an event, an action specifies the change on a more granular level. Codetally supports the following actions:
  - **added** - content was added in a commit to the repo.
  - **modified** - content was modified in a commit to the repo.
  - **removed** - content was removed in a commit to the repo.
- **Chargetype**: Charges are conditionally applied to different phases of the calculation process depending on the chargetype. Codetally currently supports the following chargetypes:
  - **author** - each commit file is individually calculated based on the author.
  - **tax** - a calculated amount added to the overall total after the other chargetypes are processed.
- **Chargeref**: An additional reference value to be used in conjunction with the `chargetype` property.
  - **For a chargetype of author** - the `chargeref` should be the author's email address used to identify the author in a commit.
  - **For a chargetype of tax** - the `chargeref` should be one of:
    - `percent` - a percentage of the subtotal will be calculated and added to the overall total cost.
    - `flat` - a flat amount will be directly added to the subtotal and the sum will be the overall total cost.
Charges defined in a `codetally.json` file are an array, so you may configure as many charges as your repo requires.    

## Time Logs

A time log is a file a software developer uses to specify the amount of time taken to complete the work in a commit.
The information is recorded in a `timelog.json` file stored at the root of the repo.
Here is an example of a `timelog.json` file:

```json
{
   "hours":"1.25",
   "minutes":"15",
   "seconds":"00"
}
```

## Installation

Installation of Codetally is a simple, 2 step process - and you can do these steps in any order:

- Create a `codetally.json` file and commit it to the root of your github repo.
- Go to [Works With Github](https://github.com/works-with?utf8=%E2%9C%93&query=Codetally) and add the Codetally application to your repo.

## Shield

After you have installed Codetally and made a few commits, you may choose to add your Codetally shield to places like your Github readme file. Your Codetally shield will be accessible through the URL http://www.codetally.com/shield/ `username`/`reponame`

**Hacker Alert!** Our servers will cache your image unless you append a random value to the end of your shield URL. So, to beat the system, add a random number like this: http://www.codetally.com/shield/ `username`/`reponame`**?1501195872560**

## Logging

Each step in the calculation process is logged on Codetally.com. To view the log of your most recent calculation, go to Codetally.com. Your log will resemble this example:

```json
[
  {
    "level": "INFO",
    "message": "The currency found was: cad",
    "timestamp": "Sat, 22-Jul-2017 01:32:23 GMT"
  },
  {
    "level": "INFO",
    "message": "The currency symbol found was: CAD",
    "timestamp": "Sat, 22-Jul-2017 01:32:23 GMT"
  },
  {
    "level": "INFO",
    "message": "Processing a commit for author: triggerman@bitbucket.org",
    "timestamp": "Sat, 22-Jul-2017 01:32:23 GMT"
  },
  {
    "level": "INFO",
    "message": "The timestamp for commit 3459a8f829f31109873aa8a03b3564968c7a5289 is 2017-07-21T21:32:00-04:00",
    "timestamp": "Sat, 22-Jul-2017 01:32:23 GMT"
  },
  {
    "level": "INFO",
    "message": "Commit type is CHARGE",
    "timestamp": "Sat, 22-Jul-2017 01:32:23 GMT"
  },
  {
    "level": "INFO",
    "message": "A modified record was found for 04-2-Methods/src/java9/Turtle.java",
    "timestamp": "Sat, 22-Jul-2017 01:32:23 GMT"
  },
  {
    "level": "INFO",
    "message": "The total amount is 23.16",
    "timestamp": "Sat, 22-Jul-2017 01:32:23 GMT"
  },
  {
    "level": "INFO",
    "message": "The current repo cost is 1326.86",
    "timestamp": "Sat, 22-Jul-2017 01:32:23 GMT"
  }
]
```

## History

Codetally maintains a history of your prior tally calculations. History records are read only and are available as a record of past activity.

## Bugs and feature requests

Have a bug or a feature request? Please [search](https://github.com/Codetally/codetally/issues?utf8=%E2%9C%93&q=is%3Aissue%20is%3Aopen%20) for existing and closed issues. If your problem or idea is not addressed yet, [please open a new issue](https://github.com/Codetally/codetally/issues/new).

## Community

Get updates on Codetally's development and chat with the project maintainers and community members.

- Implementation help may be found at Stack Overflow (tagged [`codetally`](https://stackoverflow.com/questions/tagged/codetally)).
- Community discussion happens on [Gitter](https://gitter.im/Codetally/Lobby).

## Creators

**Triggerman722**

- <https://github.com/triggerman722>


## Copyright and license

Code and documentation copyright 2017 the [Codetally Authors](https://github.com/Codetally/codetally_example/graphs/contributors). Code released under the [MIT License](https://github.com/Codetally/codetally_example/blob/master/LICENSE).
